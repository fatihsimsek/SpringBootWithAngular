import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-dashboard-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: []
})
export class VehicleListComponent implements OnInit {
  public items: any;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    return this.http.post<any>(environment.apiUrl + "vehicle/list", null).subscribe(response => {
        this.items = response;
      });
  }
}
