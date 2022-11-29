import { IBed } from 'app/shared/model/bed.model';
import { WardClassType } from 'app/shared/model/enumerations/ward-class-type.model';
import { WardLocation } from 'app/shared/model/enumerations/ward-location.model';

export interface IWard {
  id?: number;
  wardReferenceId?: string;
  wardName?: string;
  wardClassType?: WardClassType;
  wardLocation?: WardLocation;
  beds?: IBed[];
}

export class Ward implements IWard {
  constructor(
    public id?: number,
    public wardReferenceId?: string,
    public wardName?: string,
    public wardClassType?: WardClassType,
    public wardLocation?: WardLocation,
    public beds?: IBed[]
  ) {}
}
