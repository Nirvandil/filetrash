package cf.nirvandil.filetrash.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.logging.Logger;

/**
 * Created by Vladimir Sukharev aka Nirvandil on 14.05.17 at 17:29.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
@Aspect
public class LoggingAop
{
    private Logger logger = Logger.getLogger("cf.nirvandil.filetrash.controller");

    @Before("execution(* cf.nirvandil.filetrash.controller.TrashMainControllerImpl.uploadFileController(..))")
    public void logStartingUpload()
    {
        logger.warning("Test for before writing file!");
    }
}
