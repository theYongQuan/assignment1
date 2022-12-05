import { Component, OnInit, Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBed, Bed } from 'app/shared/model/bed.model';
import { BedService } from './bed.service';
import { IWard } from 'app/shared/model/ward.model';
import { WardService } from 'app/entities/ward/ward.service';

import { Title } from '@angular/platform-browser';

import * as moment from 'moment';
import { NgbDateParserFormatter, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

/**

* This Service handles how the date is rendered and parsed from keyboard i.e. in the bound input field.

*/

@Injectable()
export class CustomDateParserFormatter extends NgbDateParserFormatter {
  readonly DELIMITER = '/';

  parse(value: string): NgbDateStruct | null {
    if (value) {
      const date = value.split(this.DELIMITER);
      return {
        day: parseInt(date[0], 10),
        month: parseInt(date[1], 10),
        year: parseInt(date[2], 10)
      };
    }
    return null;
  }
  format(date: NgbDateStruct | null): string {
    return date ? date.day + this.DELIMITER + date.month + this.DELIMITER + date.year : '';
  }
}

@Component({
  selector: 'ic-bed-update',
  templateUrl: './bed-update.component.html',
  providers: [{ provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter }]
})
export class BedUpdateComponent implements OnInit {
  isSaving = false;
  wards: IWard[] = [];
  wardAllocationDateDp: any;

  editForm = this.fb.group({
    id: [],
    bedReferenceId: [null, [Validators.required, Validators.maxLength(6), Validators.pattern(/BED_(10|0[1-9])/)]],
    bedName: [null, [Validators.maxLength(17)]],
    wardAllocationDate: [null, [Validators.required]],
    wardId: [null, [Validators.required]]
  });

  constructor(
    protected bedService: BedService,
    protected wardService: WardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bed }) => {
      this.updateForm(bed);

      this.wardService.query().subscribe((res: HttpResponse<IWard[]>) => (this.wards = res.body || []));
    });
  }

  dateValidator(control: FormControl): { [s: string]: boolean } {
    if (control.value && (control.dirty || control.touched)) {
      const date = moment(control.value);
      const today = moment();
      if (date.isBefore(today, 'day')) {
        return { invalidDate: true };
      }
    }
    return { invalidDate: false };
  }

  updateForm(bed: IBed): void {
    this.editForm.patchValue({
      id: bed.id,
      bedReferenceId: bed.bedReferenceId,
      bedName: bed.bedName,
      wardAllocationDate: bed.wardAllocationDate,
      wardId: bed.wardId
    });

    if (this.editForm.get('id').value) {
      this.titleService.setTitle('Edit Ward');
    } else {
      this.titleService.setTitle('Add Ward');
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bed = this.createFromForm();
    if (bed.id !== undefined) {
      this.subscribeToSaveResponse(this.bedService.update(bed));
    } else {
      this.subscribeToSaveResponse(this.bedService.create(bed));
    }
  }

  private createFromForm(): IBed {
    return {
      ...new Bed(),
      id: this.editForm.get(['id'])!.value,
      bedReferenceId: this.editForm.get(['bedReferenceId'])!.value,
      bedName: this.editForm.get(['bedName'])!.value,
      wardAllocationDate: this.editForm.get(['wardAllocationDate'])!.value,
      wardId: this.editForm.get(['wardId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBed>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IWard): any {
    return item.id;
  }
}
