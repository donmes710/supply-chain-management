import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Badge } from "@/components/ui/badge";
import { Package, Users, ShoppingCart, TrendingUp, LogOut } from "lucide-react";
import { getAuthToken, clearAuthToken } from "@/lib/api";

const Admin = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState<{ username: string; role: string } | null>(null);

  useEffect(() => {
    const token = getAuthToken();
    if (!token) {
      navigate("/login");
      return;
    }

    // Decode JWT to get user info (simple decode, not validation)
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      setUser({
        username: payload.sub || payload.username || "Admin",
        role: payload.role || "admin"
      });
    } catch (error) {
      console.error("Failed to decode token");
      navigate("/login");
    }
  }, [navigate]);

  const handleLogout = () => {
    clearAuthToken();
    navigate("/login");
  };

  const stats = [
    { title: "Total Products", value: "1,234", icon: Package, trend: "+12%" },
    { title: "Active Orders", value: "89", icon: ShoppingCart, trend: "+8%" },
    { title: "Suppliers", value: "45", icon: Users, trend: "+3%" },
    { title: "Revenue", value: "$54.2K", icon: TrendingUp, trend: "+18%" },
  ];

  const recentOrders = [
    { id: "ORD-001", product: "Industrial Steel", supplier: "ABC Corp", status: "Delivered", amount: "$5,200" },
    { id: "ORD-002", product: "Raw Materials", supplier: "XYZ Ltd", status: "In Transit", amount: "$3,450" },
    { id: "ORD-003", product: "Electronics", supplier: "Tech Supply", status: "Processing", amount: "$8,900" },
    { id: "ORD-004", product: "Packaging", supplier: "Pack Pro", status: "Delivered", amount: "$1,200" },
  ];

  return (
    <div className="min-h-screen bg-gradient-to-br from-background to-muted/20">
      {/* Header */}
      <header className="border-b bg-background/80 backdrop-blur-sm sticky top-0 z-50">
        <div className="container mx-auto px-4 py-4 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-lg bg-gradient-to-br from-primary to-accent flex items-center justify-center">
              <Package className="h-6 w-6 text-primary-foreground" />
            </div>
            <div>
              <h1 className="text-xl font-bold">SupplyChain Pro</h1>
              <p className="text-sm text-muted-foreground">Admin Dashboard</p>
            </div>
          </div>
          <div className="flex items-center gap-4">
            <div className="text-right">
              <p className="text-sm font-medium">{user?.username}</p>
              <Badge variant="secondary" className="text-xs">{user?.role}</Badge>
            </div>
            <Button variant="outline" size="sm" onClick={handleLogout}>
              <LogOut className="h-4 w-4 mr-2" />
              Logout
            </Button>
          </div>
        </div>
      </header>

      {/* Main Content */}
      <main className="container mx-auto px-4 py-8">
        {/* Welcome Section */}
        <div className="mb-8">
          <h2 className="text-3xl font-bold mb-2">Welcome back, {user?.username}!</h2>
          <p className="text-muted-foreground">Here's what's happening with your supply chain today.</p>
        </div>

        {/* Stats Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {stats.map((stat, index) => (
            <Card key={index}>
              <CardHeader className="flex flex-row items-center justify-between pb-2">
                <CardTitle className="text-sm font-medium text-muted-foreground">
                  {stat.title}
                </CardTitle>
                <stat.icon className="h-4 w-4 text-primary" />
              </CardHeader>
              <CardContent>
                <div className="text-2xl font-bold">{stat.value}</div>
                <p className="text-xs text-success mt-1">
                  <span className="font-medium">{stat.trend}</span> from last month
                </p>
              </CardContent>
            </Card>
          ))}
        </div>

        {/* Recent Orders */}
        <Card>
          <CardHeader>
            <CardTitle>Recent Orders</CardTitle>
            <CardDescription>Latest orders in your supply chain</CardDescription>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>Order ID</TableHead>
                  <TableHead>Product</TableHead>
                  <TableHead>Supplier</TableHead>
                  <TableHead>Status</TableHead>
                  <TableHead className="text-right">Amount</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {recentOrders.map((order) => (
                  <TableRow key={order.id}>
                    <TableCell className="font-medium">{order.id}</TableCell>
                    <TableCell>{order.product}</TableCell>
                    <TableCell>{order.supplier}</TableCell>
                    <TableCell>
                      <Badge variant={
                        order.status === "Delivered" ? "default" :
                        order.status === "In Transit" ? "secondary" : "outline"
                      }>
                        {order.status}
                      </Badge>
                    </TableCell>
                    <TableCell className="text-right font-medium">{order.amount}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </CardContent>
        </Card>
      </main>
    </div>
  );
};

export default Admin;
