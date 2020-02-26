import {Component} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {AuthService} from './Services/auth.service';

class AuthenticationService {
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(
    private router: Router,
    private authService: AuthService) {
    this.router.events.subscribe(
      (event: any) => {
        if (event instanceof NavigationEnd) {
          this.autoLogin(event.url);
        }
      }
    );
  }

  autoLogin(url: string) {
    const lsToken = localStorage.getItem('token');
    if (lsToken) {
      this.authService.validate(lsToken).subscribe(() => {
        localStorage.setItem('token', lsToken);
        if (url === '/' || url === '/register' || url === '/auth') {
          this.router.navigate(['/main']);
        } else {
          this.router.navigate([url]);
        }
      }, () => {
        localStorage.removeItem('token');
        this.router.navigate(['/auth']);
      });
    } else {
      if (url === '/register') {
        this.router.navigate(['/register']);
      } else {
        this.router.navigate(['/auth']);
      }
    }
  }
}
