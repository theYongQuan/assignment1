import { Moment } from 'moment';
import { IWard } from 'app/shared/model/ward.model';

export interface IBed {
  id?: number;
  bedReferenceId?: string;
  bedName?: string;
  wardAllocationDate?: Moment;
  wardWardName?: string;
  wardId?: number;
  wards?: IWard[];
  effectiveDt?: any;
  expiryDt?: any;
}

export class Bed implements IBed {
  constructor(
    public id?: number,
    public bedReferenceId?: string,
    public bedName?: string,
    public wardAllocationDate?: Moment,
    public wardWardName?: string,
    public wardId?: number,
    public wards?: IWard[],
    public effectiveDt?: any,
    public expiryDt?: any
  ) {}
}
