import { UserRole } from '../models/User';

export interface AuthResponseDto {
  username: string;
  userRole: UserRole;
  token: string;
}
