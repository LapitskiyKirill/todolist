import {Component, OnInit} from '@angular/core';
import {faBars, faSignOutAlt, faCalendarAlt, faClipboardList} from '@fortawesome/free-solid-svg-icons';
import {faFolder} from '@fortawesome/free-regular-svg-icons/faFolder';
import {AuthService} from '../../Services/auth.service';
import {Router} from '@angular/router';
import {MenuTabs} from './Tabs';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  barIcon = faBars;
  signOutIcon = faSignOutAlt;
  calendarIcon = faCalendarAlt;
  clipboardIcon = faClipboardList;
  folderIcon = faFolder;
  tab: MenuTabs;


  constructor(
    private router: Router,
    private authService: AuthService
  ) {
  }

  ngOnInit() {
  }

  changeTab(tab: string) {
    this.tab = MenuTabs[tab.toUpperCase()];
    this.router.navigate(['/main/' + tab.toLowerCase()]);
  }

  exit() {
    this.authService.deleteToken(localStorage.getItem('token')).subscribe(() => {
      console.log('exit');
      localStorage.removeItem('token');
      this.router.navigate(['/auth']);
    });
  }
}
