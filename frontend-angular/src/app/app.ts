import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from '@auth0/auth0-angular';
import { toSignal } from '@angular/core/rxjs-interop';
import { ExampleCard } from './example/example-card/example-card'
import { NavigationComponent } from './navigation/navigation.component'

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavigationComponent, ExampleCard],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend-angular');
  private auth = inject(AuthService)
  protected readonly user = toSignal(this.auth.user$)
  protected readonly isAuthenticated = toSignal(this.auth.isAuthenticated$, {initialValue: false})
  protected readonly isLoading = toSignal(this.auth.isLoading$, {initialValue: true})
}
