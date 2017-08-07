import {Component, ViewChild, ViewEncapsulation} from '@angular/core';
import { Config } from './shared/config/env.config';
import './operators';
import {AdaptService} from "./adapt.service";
import {DxResponsiveBoxComponent} from "devextreme-angular";
import {NavigationEnd, Router} from "@angular/router";

/**
 * This class represents the main application component.
 */
@Component({
  moduleId: module.id,
  selector: 'sd-app',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [AdaptService]
})
export class AppComponent {
  @ViewChild(DxResponsiveBoxComponent) box: DxResponsiveBoxComponent;
  adaptOptions: any;
  constructor(private adapt: AdaptService, private router: Router) {
    this.adapt.adapt$.subscribe(item => {
      this.adaptOptions = this.adapt.getOptionsForAdaptation(item);
    });

    console.log('Environment config', Config);
  }
  adaptation() {
    this.adapt.setAdaptValue();
  }
  getScreen() {
    let width = window.innerWidth;

    if (width < 768)
      return "xs";
    else
      return "lg";
  }
  ngOnInit() {
    this.router.events.subscribe((val) => {
      if(val instanceof NavigationEnd){
        this.box.instance.repaint();
      }
    });
    this.adaptation();

  }
  // screen(width:any) {
  //   return ( width < 700 ) ? 'sm' : 'lg';
  // }
  helloWorld() {
    alert('Hello world!');
  }
}
