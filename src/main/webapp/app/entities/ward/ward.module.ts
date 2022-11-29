import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IconnectSampleAppLabSharedModule } from 'app/shared/shared.module';
import { WardComponent } from './ward.component';
import { WardDetailComponent } from './ward-detail.component';
import { WardUpdateComponent } from './ward-update.component';
import { WardDeleteDialogComponent } from './ward-delete-dialog.component';
import { wardRoute } from './ward.route';

@NgModule({
  imports: [IconnectSampleAppLabSharedModule, RouterModule.forChild(wardRoute)],
  declarations: [WardComponent, WardDetailComponent, WardUpdateComponent, WardDeleteDialogComponent],
  entryComponents: [WardDeleteDialogComponent]
})
export class IconnectSampleAppLabWardModule {}
