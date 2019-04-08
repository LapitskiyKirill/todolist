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

    getCategories(): Observable<Array<Category>> {
        return this.http.get<Array<Category>>(SERVER_PATH + '/category/getCategories',
            {headers: {token: '9f781057-5976-481b-bace-7868014983e5'}});

    }
}
