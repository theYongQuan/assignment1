import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWard } from 'app/shared/model/ward.model';
import { map } from 'rxjs/operators';

type EntityResponseType = HttpResponse<IWard>;
type EntityArrayResponseType = HttpResponse<IWard[]>;

@Injectable({ providedIn: 'root' })
export class WardService {
  public resourceUrl = SERVER_API_URL + 'api/wards';

  constructor(protected http: HttpClient) {}

  create(ward: IWard): Observable<EntityResponseType> {
    return this.http.post<IWard>(this.resourceUrl, ward, { observe: 'response' });
  }

  update(ward: IWard): Observable<EntityResponseType> {
    return this.http.put<IWard>(this.resourceUrl, ward, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    // const x = new HttpParams().set('id', id);
    return this.http.get<IWard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query2(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<any>> {
    const params: HttpParams = createRequestOption(req);
    return this.http
      .get(this.resourceUrl, { params, observe: 'response' })
      .pipe(map((res: HttpResponse<any>) => this.convertArrayResponse(res)));
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

  private convertItemFromServer(entity: any): any {
    const copy: IWard = Object.assign({}, entity);
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
