import { Moment } from 'moment';

export interface IBed {
  id?: number;
  bedReferenceId?: string;
  bedName?: string;
  wardAllocationDate?: Moment;
  wardWardName?: string;
  wardId?: number;
}

export class Bed implements IBed {
  constructor(
    public id?: number,
    public bedReferenceId?: string,
    public bedName?: string,
    public wardAllocationDate?: Moment,
    public wardWardName?: string,
    public wardId?: number
  ) {}
}
