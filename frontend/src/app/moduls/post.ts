import {User} from './user';
import {Comments} from './comments';
import {Tag} from './tag';
import {Like} from './like';

export class Post{

  id: number;
  photo: number;
  txt: string;
  date: string;
  user: User;
  comment: Comments[];
  tags: Tag[];
  likes: Like[];
}
