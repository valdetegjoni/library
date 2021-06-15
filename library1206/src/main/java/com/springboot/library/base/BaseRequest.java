package com.springboot.library.base;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;


public class BaseRequest implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static class Id implements Serializable {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@NotNull
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id)) return false;
            Id id1 = (Id) o;
            return Objects.equals(id, id1.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class GenId<T> implements Serializable {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@NotNull
        private T id;

        public T getId() {
            return id;
        }

        public void setId(T id) {
            this.id = id;
        }
    }

    public static class GenValue<T> implements Serializable {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@NotNull
        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

}
