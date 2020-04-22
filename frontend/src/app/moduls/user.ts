import {Ban} from './ban';
import {Post} from './post';

export class User {
  id: number;
  login: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  role: string;
  gender: string;
  status: string;
  ban: Ban[];
  posts: Post[];
}
