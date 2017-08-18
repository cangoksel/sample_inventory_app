import {Http, RequestOptions,Response,Headers} from "@angular/http";
import { Adres } from '../_models/index';
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AdresService {
  constructor(private http: Http) {
  }

  getAll():Observable<Adres[]> {
    return this.http.get('/api/adres/list').map((response: Response) =>response.json());
  }

  private jwt() {
    // create authorization header with jwt token
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.token) {
      let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
      return new RequestOptions({ headers: headers });
    }
    return null;
  }
}
