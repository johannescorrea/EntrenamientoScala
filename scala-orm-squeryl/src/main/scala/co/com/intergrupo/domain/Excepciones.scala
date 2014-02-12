package co.com.intergrupo.domain

class AutoNoValidoException(message:String) extends Exception(message) {
  
  object AutoNoValidoException {
    def create (msg: String): AutoNoValidoException = new AutoNoValidoException(msg)
    
    def create (msg: String, cause: Throwable): AutoNoValidoException = {
      var ex = new AutoNoValidoException(msg)
      ex.initCause(cause)
      ex
    }
  }
  
}

class SubastaException(message:String) extends Exception(message) {}