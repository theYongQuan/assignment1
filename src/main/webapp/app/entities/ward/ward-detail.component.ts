import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWard } from 'app/shared/model/ward.model';
import { Title } from '@angular/platform-browser';

import { IBed } from 'app/shared/model/bed.model';
import { BedService } from '../bed/bed.service';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'ic-ward-detail',
  templateUrl: './ward-detail.component.html'
})
export class WardDetailComponent implements OnInit {
  ward: IWard | null = null;
  beds?: IBed[];
  itemsPerPage: any;
  page: number;
  totalItems: number;
  predicate: string;
  ascending: any;
  ngbPaginationPage: number;

  constructor(protected activatedRoute: ActivatedRoute, private titleService: Title, protected bedService: BedService) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;
    this.bedService
      .query({
        'wardId.equals': this.ward.id,
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IBed[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ward }) => (this.ward = ward));
    this.titleService.setTitle('View Ward');

    this.bedService.query().subscribe((res: HttpResponse<IBed[]>) => {
      this.beds = res.body || [];
    });

    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
  }

  previousState(): void {
    window.history.back();
  }

  getBedCount(id): number {
    return this.beds.filter(bed => bed.wardId === id).length;
  }

  trackId(index: any, item: IWard): any {
    return item.wardName;
  }

  protected onSuccess(data: IBed[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    /* this.router.navigate(['/ward'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    }); */
    this.beds = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
