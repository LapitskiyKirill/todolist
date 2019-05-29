import {Component, OnInit} from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import * as moment from 'moment';
import {NewScheduled} from '../../dto/NewScheduled';
import {Periodicity} from '../../dto/Periodicity';
import {ScheduledService} from '../../service/scheduled.service';

@Component({
    selector: 'app-make-scheduled',
    templateUrl: './make-scheduled.component.html',
    styleUrls: ['./make-scheduled.component.scss']
})
export class MakeScheduledComponent implements OnInit {
    public periodicities: string[] = ['ONCE', 'DAILY', 'WEEKLY', 'MONTHLY', 'ANNUALLY'];
    public taskId: number;
    public newScheduled: NewScheduled = new NewScheduled(null, moment(Date.now()).format('YYYY-MM-DDTHH:mm'), '' );

    constructor(private app: AppComponent,
                private activatedRoute: ActivatedRoute,
                private scheduledService: ScheduledService,
                private router: Router) {
    }

    schedule() {
        this.newScheduled.taskId = this.taskId;
        console.log(this.newScheduled.from);
        this.scheduledService.create(localStorage.getItem('token'), this.newScheduled).subscribe();
    }

    ngOnInit() {
        this.app.onLoad(() => {
            this.activatedRoute.params.subscribe(params => this.taskId = params['taskId']);
        });

    }
}

