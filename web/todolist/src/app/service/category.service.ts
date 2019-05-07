import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_PATH} from '../../globals';
import {Category} from '../dto/Category';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {

    constructor(private http: HttpClient) {
    }

    getCategories(token: string): Observable<Category[]> {
        return this.http.get<Category[]>(SERVER_PATH + '/category/getCategories',
            {headers: {token: token}});

    }
}
