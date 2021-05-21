export class Alert {
    
    public type: AlertType = AlertType.Info;
    public message: string = '';
    public alertId: string = '';
    public keepAfterRouteChange: boolean = false;
    public display: string = '';

    constructor(init?:Partial<Alert>) {
        Object.assign(this, init);
    }
}

export enum AlertType {
    Success,
    Error,
    Info,
    Warning
}