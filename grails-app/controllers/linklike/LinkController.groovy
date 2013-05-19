package linklike

class LinkController {

    def index() { 
        def links = Link.findAll()
        [ links: links ]
    }

    def create() {
        def link = new Link(title: params.title, url: params.url)
        link.save()
        redirect(action: "index")
    }
    
    def addvote(String title){
    	def links = Link.findAll()
    	for (l in links){
    		if(l.title==title){
    			l.votecnt+=1
    			break
    		}
    	}
    }

    def unvote(String title){
    	def links = Link.findAll()
    	for (l in links){
    		if(l.title==title){
    			l.votecnt-=1
    			break
    		}
    	}
    }
}
