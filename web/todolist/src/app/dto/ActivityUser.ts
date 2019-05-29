export class ActivityUser {
    id: number;
    text: string;
    complete: Date;
    date: Date;

    constructor(id: number, text: string, complete: Date, date: Date) {
        this.id = id;
        this.text = text;
        this.complete = complete;
        this.date = date;
    }
}
