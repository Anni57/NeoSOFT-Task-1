import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { DeleteDialogComponent } from 'src/app/common-components/delete-dialog/delete-dialog.component';
import { ActionType } from 'src/app/_models/ActionType';
import { User } from 'src/app/_models/User';
import { DataService } from 'src/app/_services/data.service';
import { SaveUserComponent } from './save-user/save-user.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  users!: User[];
  displayedColumns = [
    'serialNo',
    'userName',
    'email',
    'contactNumber',
    'actions',
  ];
  dataSource = new MatTableDataSource<User>(this.users);

  constructor(private dataService: DataService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    let url = 'user/getAll';
    let requestData = {};
    this.dataService.postMethodWithHeader(url, requestData).subscribe({
      next: (responseData: any) => {
        if (this.dataService.isResponseOkAndNonNull(responseData)) {
          const usersData = responseData.response;
          this.users = usersData;
          this.dataSource = new MatTableDataSource<User>(this.users);
        }
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  addUser(): void {
    const dialogRef = this.dialog.open(SaveUserComponent, {
      width: '40%',
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (ActionType.REFRESH === result) {
        this.getUsers();
      }
    });
  }

  onEdit(userId: number) {
    const dialogRef = this.dialog.open(SaveUserComponent, {
      width: '40%',
      disableClose: true,
      data: { actionType: ActionType.EDIT, userId: userId },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (ActionType.REFRESH === result) {
        this.getUsers();
      }
    });
  }

  onDelete(userId: number) {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: '30%',
      height: '30%',
      disableClose: true,
      data: { id: userId, url: 'user/disable' },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (ActionType.REFRESH === result) {
        this.getUsers();
      }
    });
  }
}
