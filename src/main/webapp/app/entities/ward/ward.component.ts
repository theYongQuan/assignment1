import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWard } from 'app/shared/model/ward.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WardService } from './ward.service';
import { WardDeleteDialogComponent } from './ward-delete-dialog.component';
import { IBed } from 'app/shared/model/bed.model';
import { BedService } from '../bed/bed.service';
import { CodeView, IcCodeService } from 'app/ng-iconnect';
// import { listLazyRoutes } from '@angular/compiler/src/aot/lazy_routes';

@Component({
  selector: 'ic-ward',
  templateUrl: './ward.component.html'
})
export class WardComponent implements OnInit, OnDestroy {
  public wardClassTypeDatas: CodeView[] = [];
  public wardLocationDatas: CodeView[] = [];
  wards?: IWard[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  searchCriteria: any;
  routeData: any;
  previousPage: any;
  reverse: any;
  links: any;
  beds?: IBed[];
  constructor(
    protected codeService: IcCodeService,
    protected wardService: WardService,
    protected bedService: BedService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data['pagingParams'].page;
      this.previousPage = data['pagingParams'].page;
      this.reverse = data['pagingParams'].ascending;
      this.predicate = data['pagingParams'].predicate;
    });

    this.searchCriteria = {
      wardName: ''
    };
  }

  getBedCount(id): number {
    return this.beds.filter(bed => bed.wardId === id).length;
  }

  search(): void {
    this.links = {
      last: 0
    };
    this.page = 0;
    this.predicate = 'wardReferenceId';
    this.reverse = false;
    this.loadAll();
  }

  clear(): void {
    this.searchCriteria = {
      wardName: ''
    };
    this.loadPage(this.page);
  }

  loadAll(): void {
    this.wardService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
        'wardName.contains': this.searchCriteria.wardName
      })
      .subscribe(
        (res: HttpResponse<IWard[]>) => this.onSuccess(res.body, res.headers, this.page),
        () => this.onError()
      );
  }

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.wardService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IWard[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.codeService.gets('wardclasstype').subscribe((datas: any[]) => {
      this.wardClassTypeDatas = datas[0];
    });

    this.codeService.gets('wardlocation').subscribe((datas: any[]) => {
      this.wardLocationDatas = datas[0];
    });

    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.bedService.query().subscribe((res: HttpResponse<IBed[]>) => {
      this.beds = res.body || [];
    });
    this.registerChangeInWards();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWard): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWards(): void {
    this.eventSubscriber = this.eventManager.subscribe('wardListModification', () => this.loadPage());
  }

  delete(ward: IWard): void {
    const modalRef = this.modalService.open(WardDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ward = ward;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'wardReferenceId') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IWard[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/ward'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.wards = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
