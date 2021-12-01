import { Component, Inject, OnInit } from '@angular/core';
import { DataService } from 'src/app/_services/data.service';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActionType } from 'src/app/_models/ActionType';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.css'],
})
export class DeleteDialogComponent implements OnInit {
  id!: number;
  url!: string;
  constructor(
    private dataService: DataService,
    private matDialogRef: MatDialogRef<DeleteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: { id: number; url: string }
  ) {}

  ngOnInit(): void {
    this.id = +this.data.id;
    this.url = this.data.url;
  }

  onCancel(): void {
    this.matDialogRef.close();
  }

  onConfirmDelete(): void {
    if (this.url != undefined && this.url != null && this.url != '') {
      let requestData: any = {};
      requestData['id'] = this.id;
      this.dataService.postMethodWithHeader(this.url, requestData).subscribe({
        next: (responseData: any) => {
          if (this.dataService.isResponseOkAndNonNull(responseData)) {
            this.matDialogRef.close(ActionType.REFRESH);
          }
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
}
