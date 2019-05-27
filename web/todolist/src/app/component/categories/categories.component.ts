import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../service/category.service';
import {TaskService} from '../../service/task.service';
import {TokenProvider} from '../../provider/token.provider';
import {Category} from '../../dto/Category';
import {AppComponent} from '../../app.component';

@Component({
    selector: 'app-categories',
    templateUrl: './categories.component.html',
    styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {
    categories: Category[];

    constructor(
        private app: AppComponent,
        private categoryService: CategoryService,
        private taskService: TaskService,
        private tokenProvider: TokenProvider) {
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
