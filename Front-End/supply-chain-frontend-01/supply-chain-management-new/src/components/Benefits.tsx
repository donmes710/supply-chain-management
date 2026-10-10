import { CheckCircle2 } from "lucide-react";

const benefits = [
  "Reduce operational costs by up to 40%",
  "Improve order accuracy and reduce errors",
  "Real-time visibility across your supply chain",
  "Automated workflows save hours of manual work",
  "Scalable solution that grows with your business",
  "24/7 customer support from our expert team",
];

const Benefits = () => {
  return (
    <section id="benefits" className="py-20 px-4 bg-muted/30">
      <div className="container mx-auto max-w-6xl">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          <div className="space-y-6">
            <h2 className="text-4xl md:text-5xl font-bold">
              Why Choose{" "}
              <span className="bg-gradient-to-r from-primary to-accent bg-clip-text text-transparent">
                SupplyChain Pro
              </span>
            </h2>
            <p className="text-lg text-muted-foreground">
              Join thousands of businesses that have transformed their supply chain operations 
              with our powerful platform. Experience the difference of intelligent automation 
              and real-time insights.
            </p>

            <div className="space-y-4 pt-4">
              {benefits.map((benefit, index) => (
                <div key={index} className="flex items-start gap-3">
                  <CheckCircle2 className="h-6 w-6 text-primary flex-shrink-0 mt-0.5" />
                  <span className="text-foreground">{benefit}</span>
                </div>
              ))}
            </div>
          </div>

          <div className="relative">
            <div className="aspect-square rounded-2xl bg-gradient-to-br from-primary/20 via-accent/20 to-primary/20 p-8 flex items-center justify-center">
              <div className="w-full h-full bg-card rounded-xl shadow-2xl border border-border p-8 flex flex-col justify-center">
                <div className="space-y-6">
                  <div className="h-4 bg-primary/20 rounded w-3/4 animate-pulse"></div>
                  <div className="h-4 bg-primary/10 rounded w-full animate-pulse delay-75"></div>
                  <div className="h-4 bg-primary/10 rounded w-5/6 animate-pulse delay-150"></div>
                  <div className="grid grid-cols-2 gap-4 pt-4">
                    <div className="h-24 bg-primary/5 rounded-lg animate-pulse"></div>
                    <div className="h-24 bg-primary/5 rounded-lg animate-pulse delay-75"></div>
                  </div>
                  <div className="grid grid-cols-3 gap-4">
                    <div className="h-16 bg-primary/5 rounded-lg animate-pulse delay-150"></div>
                    <div className="h-16 bg-primary/5 rounded-lg animate-pulse"></div>
                    <div className="h-16 bg-primary/5 rounded-lg animate-pulse delay-75"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Benefits;
