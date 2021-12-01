export class User {
  private id!: string;
  private userName!: string;
  private email!: string;
  private contactNumber!: string;

  public User(
    id: string,
    userName: string,
    email: string,
    contactNumber: string
  ) {
    this.id = id;
    this.userName = userName;
    this.email = email;
    this.contactNumber = contactNumber;
  }
}
