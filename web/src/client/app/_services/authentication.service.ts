import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    constructor(private http: Http) { }

    login(username: string, password: string) {
      const formData = new FormData();
      formData.append("username", username);
      formData.append("password", password);

        return this.http.post('/api/login', formData)
            .map((response: Response) => {
                if (response.ok) {
                  return "success";
                }
                return "failure";
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}
