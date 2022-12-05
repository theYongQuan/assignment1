import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBed } from 'app/shared/model/bed.model';

type EntityResponseType = HttpResponse<IBed>;
type EntityArrayResponseType = HttpResponse<IBed[]>;

@Injectable({ providedIn: 'root' })
export class BedService {
  public resourceUrl = SERVER_API_URL + 'api/beds';

  constructor(protected http: HttpClient) {}

  create(bed: IBed): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bed);
    return this.http
      .post<IBed>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bed: IBed): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bed);
    return this.http
      .put<IBed>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBed>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query2(req?: any): Observable<HttpResponse<any>> {
    const params: HttpParams = createRequestOption(req);
    return this.http
      .get(this.resourceUrl, { params, observe: 'response' })
      .pipe(map((res: HttpResponse<any>) => this.convertArrayResponse(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBed[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  private convertArrayResponse(res: HttpResponse<any[]>): HttpResponse<any[]> {
    const jsonResponse: any[] | null = res.body;
    const body: any[] = [];
    if (jsonResponse) {
      for (let i = 0; i < jsonResponse.length; i++) {
        body.push(this.convertItemFromServer(jsonResponse[i]));
      }
    }
    return res.clone({ body });
  }

  protected convertDateFromClient(bed: IBed): IBed {
    const copy: IBed = Object.assign({}, bed, {
      wardAllocationDate:
        bed.wardAllocationDate && bed.wardAllocationDate.isValid() ? bed.wardAllocationDate.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.wardAllocationDate = res.body.wardAllocationDate ? moment(res.body.wardAllocationDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bed: IBed) => {
        bed.wardAllocationDate = bed.wardAllocationDate ? moment(bed.wardAllocationDate) : undefined;
      });
    }
    return res;
  }

  private convertItemFromServer(entity: any): any {
    const copy: IBed = Object.assign({}, entity);
    if (copy.effectiveDt) {
      const dateString = copy.effectiveDt.split('/');
      copy.effectiveDt = new Date(dateString[2], dateString[0] - 1, dateString[1]);
    }
    if (copy.expiryDt) {
      const dateString = copy.expiryDt.split('/');
      copy.expiryDt = new Date(dateString[2], dateString[0] - 1, dateString[1]);
    }
    return copy;
  }
}
