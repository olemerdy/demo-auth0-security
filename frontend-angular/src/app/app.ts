import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { YouTubePlayer } from '@angular/youtube-player';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { AuthService } from '@auth0/auth0-angular';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatButtonModule, MatCardModule, RouterOutlet, YouTubePlayer],
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
