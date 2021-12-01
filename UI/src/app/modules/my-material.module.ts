import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';

const myMaterialModules = [
  MatTableModule,
  MatPaginatorModule,
  MatFormFieldModule,
  MatTooltipModule,
  MatButtonModule,
  MatDialogModule,
  MatDividerModule,
  MatInputModule,
  MatIconModule,
  MatMenuModule,
];

@NgModule({
  imports: [myMaterialModules],
  exports: [myMaterialModules],
})
export class MyMaterialModule {}
