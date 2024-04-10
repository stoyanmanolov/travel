import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Injectable } from '@angular/core';
import { Observable, map, shareReplay } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ResponsiveService {
  private readonly SMALL = [Breakpoints.XSmall, Breakpoints.Small];
  private readonly MEDIUM = [...this.SMALL, Breakpoints.Medium];

  constructor(private breakpointObserver: BreakpointObserver) {}

  isSmall$: Observable<boolean> = this.breakpointObserver
    .observe(this.SMALL)
    .pipe(
      map((result) => result.matches),
      shareReplay()
    );

  isMedium$: Observable<boolean> = this.breakpointObserver
    .observe(this.MEDIUM)
    .pipe(
      map((result) => result.matches),
      shareReplay()
    );
}
