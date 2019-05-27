import {Component, OnInit} from '@angular/core';
import {Category} from '../../dto/Category';
import {CategoryService} from '../../service/category.service';
import {TokenProvider} from '../../provider/token.provider';
import {AppComponent} from '../../app.component';

@Component({
    selector: 'app-create-task',
    templateUrl: './create-task.component.html',
    styleUrls: ['./create-task.component.scss']
})
export class CreateTaskComponent implements OnInit {

    categories: Category[];

    constructor(
        private app: AppComponent,
        private categoryService: CategoryService,
        private tokenProvider: TokenProvider
    ) {
    }

    ngOnInit() {
        this.app.onLoad(() => {
            this.tokenProvider.token.subscribe(t => {
                this.categoryService.getCategories(t).subscribe(cs => {
                    this.categories = cs;
                });
            });
        });
    }
}
