import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TokenService} from '../../service/token.service';
import {TokenProvider} from '../../provider/token.provider';
import {Router} from '@angular/router';


@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

    constructor(private http: HttpClient,
                private tokenService: TokenService,
                private tokenProvider: TokenProvider,
                private router: Router
    ) {
    }

    logout() {
        console.log('logout');
        this.tokenService.remove(localStorage.getItem('token')).subscribe(success => {
            localStorage.removeItem('token');
            this.router.navigate(['/auth']);
        });
        console.log('logout3');
    }

    ngOnInit() {
    }


}
