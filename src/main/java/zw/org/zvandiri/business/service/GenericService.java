/*
 * Copyright 2014 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zw.org.zvandiri.business.service;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Judge Muzinda
 * @param <T> service implementation interface 
 */
public interface GenericService<T extends Serializable> extends Serializable {
    
    public List<T> getAll();
    public T get(String id);
    public void delete(T t);
    public List<T> getPageable();
    public T save(T t);
    public Boolean checkDuplicate(T current, T old);
}