 - @Autowired is unneceseary when you do constructor based injection.
 - Added private finals to follow a good convention.
 - Also lombok for readibility but thats up to you. Imo nothing wrong wth using it.
 - @ResponseBody is not necessary, spring will automatically convert to json - comment wrote by copilot wtf
 - Methods in controllers don't have to be public
 - ScheduleId -> Id in controller since we already are in schedule context. If more ids appear, then you can reconsider.
 - Everything except for DTOs mappers and ScheduleFacade is package scope. Now you know exactly where to start testing (controller, facade, adapter)
 - No need for SpringData prefix
 - Facade = ApplicationService, Service = DomainService
 - Entity logic in entity. Entity logic with an extra in Service. Orchestrating entity logic in Facade. 
 - Java based bean config > Annotation based bean config - easier to understand how spring works, gives more flexibility, no need to fuck around with qualifiers


---
ServicePersistentAdapter servicePersistentAdapter in controller -> this is **_HARAM_** 

What I believe here is you attempted to implement Q part of CQRS which is great but it still should be done like Allah intended. 

And by Allah I mean this is my opinion which I'll try to logically explain.

Pretty much if you stay consistent with package scope in all of infra then this issue would not occur.
      
The only way to access infrastructure is via DOMAIN PORTS. No other way. That's the entire gist of HA.

You cannot communicate between adapters directly. It should be orchestrated by domain based on business needs.

You can treat every infrastructure package as a black box.

You don't know what's inside. You don't care. You don't even know they exist (from the perspective of other infra packages)

Again remember the curved arrow you asked me about when I made HA presentation?

It was supposed to represent inputAdapter -> domain -> outputAdapter -> domain -> inputAdapter flow

But in general your idea was good. You just need to add a port in domain and use it in controller.

For now tho having a method in facade is enough. When you split adapters for read/write then you can consider adding an input port.


--- 
Another point about HA which is strictly my opinion here.

I don't think it's needed to use interface for input ports tbh.

It's probably just a matter of preference but if you think about it there isn't any serious benefit on doing so.

You just add one more layer of abstraction and input ports will probably never have more than one implementation.

But it's up to you really. I for example used facade here which imo fits pretty well. It's the same thing as application service in sianotracker.

When we use facade we can drop the ApplicationService/DomainService split and just use Service. This maybe produces less confusion.

So ApplicationService = Facade, DomainService = Service.

But again it's up to you.