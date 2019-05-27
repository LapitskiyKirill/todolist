export class EditTask {
    text: string;
    deadline: string;
    category: string;
    constructor(text: string, deadline: string, category: string) {
        this.text = text;
        this.deadline = deadline;
        this.category = category;
    }
}
