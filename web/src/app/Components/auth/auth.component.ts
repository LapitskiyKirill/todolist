import {Component, OnInit} from '@angular/core';
import {AuthUser} from '../../Entities/AuthUser';
import {Router} from '@angular/router';
import {AuthService} from '../../Services/auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {
  public authUser: AuthUser = new AuthUser();

  constructor(private router: Router,
              private authService: AuthService
  ) {
  }

  public auth() {
    if (this.authUser.login !== null && this.authUser !== null) {
      this.authService.auth(this.authUser).subscribe(t => {
        localStorage.setItem('token', t);
        this.router.navigate(['']);
      });
    }
  }

  ngOnInit() {
  }

}
