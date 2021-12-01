import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActionType } from 'src/app/_models/ActionType';
import { DataService } from 'src/app/_services/data.service';

@Component({
  selector: 'app-save-user',
  templateUrl: './save-user.component.html',
  styleUrls: ['./save-user.component.css'],
})
export class SaveUserComponent implements OnInit {
  user!: FormGroup;
  isDisabled: boolean = false;
  isEdit: boolean = false;
  constructor(
    private dataService: DataService,
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<SaveUserComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any
  ) {}

  ngOnInit(): void {
    if (this.data && this.data.actionType === ActionType.EDIT) {
      this.isEdit = true;
    }

    this.user = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.minLength(5)]],
      email: ['', [Validators.required, Validators.email]],
      contactNumber: ['', Validators.required],
    });

    if (this.isEdit) this.loadUserDetails(this.data.userId);
  }

  loadUserDetails = (userId: number) => {
    let url = 'user/loadById';
    let requestData: any = {};
    requestData['userMasterId'] = userId;
    this.dataService.postMethodWithHeader(url, requestData).subscribe({
      next: (responseData: any) => {
        if (this.dataService.isResponseOkAndNonNull(responseData)) {
          const userDetails = responseData.response;
          this.user.get('userName')?.setValue(userDetails.userName);
          this.user.get('email')?.setValue(userDetails.email);
          this.user.get('contactNumber')?.setValue(userDetails.contactNumber);
        }
      },
      error: (err) => {
        console.log(err);
      },
    });
  };

  onUserSave(): void {
    this.isDisabled = true;

    let url = 'user/save';

    let requestData: any = {};
    if (this.isEdit) requestData['userMasterId'] = this.data.userId;
    requestData['userDetails'] = this.user.value;
    this.dataService.postMethodWithHeader(url, requestData).subscribe({
      next: (responseData: any) => {
        this.isDisabled = false;
        if (this.dataService.isResponseOk(responseData)) {
          this.dialogRef.close(ActionType.REFRESH);
        }
      },
      error: (err: any) => {
        this.isDisabled = false;
        console.log(err);
      },
    });
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
