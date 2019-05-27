import {Category} from './Category';

export class NewTask {
    text: string;
    deadline: string;
    category: Category;
    subtasks: NewTask[];
}

