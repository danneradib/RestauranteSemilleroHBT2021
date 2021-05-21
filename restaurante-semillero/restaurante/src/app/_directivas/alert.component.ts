import { Component, OnInit } from '@angular/core';

import { Alert, AlertType } from '../_modelos/alert';
import { AlertService } from '../_servicios/alert.service';

@Component({
    moduleId: module.id,
    selector: 'alert',
    templateUrl: 'alert.component.html'
})

export class AlertComponent {
    alerts: Alert[] = [];
    display='none';

    constructor(private alertService: AlertService) { }

    ngOnInit() {
        this.alertService.getAlert().subscribe((alert: Alert) => {
            if (!alert) {
                // Se limpian las alertas cuando el arrreglo no recibe elementos
                this.alerts = [];
                return;
            }

            // Se adiciona la alerta al arreglo
            this.alerts.push(alert);
        });
    }

    removeAlert(alert: Alert) {
        this.alerts = this.alerts.filter(x => x !== alert);
    }

    cssClass(alert: Alert) {
        if (!alert) {
            return;
        }

        // retorna la clase CSS segun el tipo de alerta
        switch (alert.type) {
            case AlertType.Success:
                return 'alert alert-success';
            case AlertType.Error:
                return 'alert alert-danger';
            case AlertType.Info:
                return 'alert alert-info';
            case AlertType.Warning:
                return 'alert alert-warning';
        }
    }
}