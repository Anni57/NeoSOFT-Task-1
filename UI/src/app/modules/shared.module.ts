import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DeleteDialogComponent } from '../common-components/delete-dialog/delete-dialog.component';
import { UserModule } from '../components/user/user.module';
import { MyMaterialModule } from './my-material.module';

const sharedModules = [DeleteDialogComponent];

@NgModule({
  declarations: [sharedModules],
  imports: [
    CommonModule,
    MyMaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    UserModule,
  ],
  exports: [sharedModules],
})
export class SharedModule {}
