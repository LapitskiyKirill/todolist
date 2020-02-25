import {Component, OnInit} from '@angular/core';
import {faBars, faPlusSquare, faSignOutAlt} from '@fortawesome/free-solid-svg-icons';
import {AuthService} from '../../Services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  barIcon = faBars;
  plusIcon = faPlusSquare;
  signOutIcon = faSignOutAlt;

  constructor(
    private router: Router,
    private authService: AuthService
  ) {
  }

  ngOnInit() {
  }

  exit() {
    this.authService.deleteToken(localStorage.getItem('token')).subscribe(() => {
      localStorage.removeItem('token');
      this.router.navigate(['/auth']);
    });
  }
}
