import {Category} from './Category';

export class NewTask {
    text: string;
    deadline: Date;
    category: Category;
    subtasks: NewTask[];
}
