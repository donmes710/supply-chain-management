import { Package, ShoppingCart, Truck, BarChart3, Users, Shield } from "lucide-react";

const features = [
  {
    icon: ShoppingCart,
    title: "Order Management",
    description: "Track and manage all your orders in one centralized system with real-time updates.",
  },
  {
    icon: Package,
    title: "Product Control",
    description: "Complete visibility over your product inventory with automated tracking and alerts.",
  },
  {
    icon: Truck,
    title: "Supply Tracking",
    description: "Monitor your entire supply chain from vendors to delivery with precision.",
  },
  {
    icon: BarChart3,
    title: "Analytics Dashboard",
    description: "Make data-driven decisions with comprehensive analytics and reporting tools.",
  },
  {
    icon: Users,
    title: "Multi-user Access",
    description: "Role-based access control for admins and managers with secure permissions.",
  },
  {
    icon: Shield,
    title: "Secure & Reliable",
    description: "Enterprise-grade security with JWT authentication and encrypted data storage.",
  },
];

const Features = () => {
  return (
    <section id="features" className="py-20 px-4 bg-background">
      <div className="container mx-auto max-w-6xl">
        <div className="text-center space-y-4 mb-16">
          <h2 className="text-4xl md:text-5xl font-bold">
            Everything You Need to{" "}
            <span className="bg-gradient-to-r from-primary to-accent bg-clip-text text-transparent">
              Manage Your Supply Chain
            </span>
          </h2>
          <p className="text-lg text-muted-foreground max-w-2xl mx-auto">
            A comprehensive platform designed to streamline every aspect of your operations.
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {features.map((feature, index) => (
            <div
              key={index}
              className="p-6 rounded-lg border border-border bg-card hover:shadow-lg transition-all duration-300 hover:border-primary/50 group"
            >
              <div className="w-12 h-12 rounded-lg bg-primary/10 flex items-center justify-center mb-4 group-hover:bg-primary/20 transition-colors">
                <feature.icon className="h-6 w-6 text-primary" />
              </div>
              <h3 className="text-xl font-semibold mb-2">{feature.title}</h3>
              <p className="text-muted-foreground">{feature.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Features;
