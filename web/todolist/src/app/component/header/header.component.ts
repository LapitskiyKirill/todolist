import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SERVER_PATH} from '../../../globals';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

    constructor(private http: HttpClient,
    ) {
    }

    logout() {
        this.http.get(SERVER_PATH + '/token/delete', {
            params: {
                token: localStorage.getItem('token')
            }
        }).subscribe();
    }


    ngOnInit() {
    }


}
