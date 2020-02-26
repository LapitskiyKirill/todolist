import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SERVER_PATH} from '../../globals';
import {Observable} from 'rxjs';
import {Category} from '../Entities/Category';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private http: HttpClient) {
  }

  getCategories(token: string): Observable<Category[]> {
    return this.http.get<Category[]>(SERVER_PATH + '/category/getCategories', {
      headers: {
        token
      }
    });
  }
}
