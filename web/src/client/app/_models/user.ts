export class User {
    id: number;
    username: string;
    password: string;
    firstName: string;
    lastName: string;

  constructor(name: string){
      this.username=name;
    }
}
