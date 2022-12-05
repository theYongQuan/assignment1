import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWard, Ward } from 'app/shared/model/ward.model';
import { WardService } from './ward.service';

import { CodeView, IcCodeService } from 'app/ng-iconnect';
import { Title } from '@angular/platform-browser';
// import { faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'ic-ward-update',
  templateUrl: './ward-update.component.html'
})
export class WardUpdateComponent implements OnInit {
  isSaving = false;
  isDuplicateField = false;
  isDuplicateFieldName = false;

  public wardClassTypeDatas: CodeView[] = [];
  public wardLocationDatas: CodeView[] = [];
  originalWardId: String;
  originalWardLocation: String;

  wards?: IWard[];

  editForm = this.fb.group({
    id: [],
    wardReferenceId: [null, [Validators.required, Validators.maxLength(7), Validators.pattern(/WARD_(10|0[1-9])/)]],
    wardName: [null, [Validators.required, Validators.maxLength(10)]],
    wardClassType: [null, [Validators.required]],
    wardLocation: [null, [Validators.required]]
  });

  constructor(
    protected codeService: IcCodeService,
    protected wardService: WardService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.codeService.gets('wardclasstype').subscribe((datas: any[]) => {
      this.wardClassTypeDatas = datas[0];
    });

    this.codeService.gets('wardlocation').subscribe((datas: any[]) => {
      this.wardLocationDatas = datas[0];
    });

    this.activatedRoute.data.subscribe(({ ward }) => {
      this.updateForm(ward);
    });

    this.originalWardId = this.editForm.get(['wardReferenceId'])!.value;
    this.originalWardLocation = this.editForm.get(['wardLocation'])!.value;
  }

  updateForm(ward: IWard): void {
    this.editForm.patchValue({
      id: ward.id,
      wardReferenceId: ward.wardReferenceId,
      wardName: ward.wardName,
      wardClassType: ward.wardClassType,
      wardLocation: ward.wardLocation
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

  onBlur(): void {
    if (this.editForm.get('id').value) {
      // is edit ward page
      if (!(this.originalWardId === this.editForm.get(['wardReferenceId'])!.value)) {
        this.wardService
          .query({
            'wardReferenceId.equals': this.editForm.get(['wardReferenceId'])!.value
          })
          .subscribe(
            (data: any) => {
              if (data?.body?.length > 0) {
                this.isDuplicateField = true;
              } else {
                this.isDuplicateField = false;
              }
            } /* ,(error)=>{} */
          );
      }
    } else {
      // is add ward page
      this.wardService
        .query({
          'wardReferenceId.equals': this.editForm.get(['wardReferenceId'])!.value
        })
        .subscribe(
          (data: any) => {
            if (data?.body?.length > 0) {
              this.isDuplicateField = true;
            } else {
              this.isDuplicateField = false;
            }
          } /* ,(error)=>{} */
        );
    }
  }

  onBlurName(): void {
    if (this.editForm.get('id').value) {
      // is edit ward page
      if (!(this.originalWardLocation === this.editForm.get(['wardName'])!.value)) {
        this.wardService
          .query({
            'wardName.equals': this.editForm.get(['wardName'])!.value
          })
          .subscribe(
            (data: any) => {
              if (data?.body?.length > 0) {
                this.isDuplicateFieldName = true;
              } else {
                this.isDuplicateFieldName = false;
              }
            } /* ,(error)=>{} */
          );
      }
    } else {
      // is add ward page
      this.wardService
        .query({
          'wardName.equals': this.editForm.get(['wardName'])!.value
        })
        .subscribe(
          (data: any) => {
            if (data?.body?.length > 0) {
              this.isDuplicateFieldName = true;
            } else {
              this.isDuplicateFieldName = false;
            }
          } /* ,(error)=>{} */
        );
    }
  }

  save(): void {
    this.isSaving = true;
    const ward = this.createFromForm();

    if (ward.id !== undefined) {
      this.subscribeToSaveResponse(this.wardService.update(ward));
    } else {
      this.subscribeToSaveResponse(this.wardService.create(ward));
    }
  }

  private createFromForm(): IWard {
    return {
      ...new Ward(),
      id: this.editForm.get(['id'])!.value,
      wardReferenceId: this.editForm.get(['wardReferenceId'])!.value,
      wardName: this.editForm.get(['wardName'])!.value,
      wardClassType: this.editForm.get(['wardClassType'])!.value,
      wardLocation: this.editForm.get(['wardLocation'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWard>>): void {
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
}
