import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MyMaterialModule } from 'src/app/modules/my-material.module';
import { SaveUserComponent } from './save-user/save-user.component';
import { UserComponent } from './user.component';

@NgModule({
  declarations: [SaveUserComponent],
  imports: [CommonModule, MyMaterialModule, ReactiveFormsModule],
})
export class UserModule {}
