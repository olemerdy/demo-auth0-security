import { Component } from '@angular/core';
import { YouTubePlayer } from '@angular/youtube-player';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-example-card',
  imports: [MatButtonModule, MatCardModule, YouTubePlayer, ],
  templateUrl: './example-card.html',
  styleUrl: './example-card.css',
})
export class ExampleCard {

}
