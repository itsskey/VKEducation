package pages;

import pages.elements.SidebarWrapper;
import pages.elements.ToolbarWrapper;

public class HomePage implements BasePage{
    // Тулбар
    private static final ToolbarWrapper TOOLBAR = new ToolbarWrapper();
    // Боковое меню
    private static final SidebarWrapper SIDEBAR = new SidebarWrapper();

    public HomePage() {
        isLoaded(); // Автоматическая проверка при создании страницы
    }

    @Override
    public HomePage isLoaded() {
        SIDEBAR.isLoaded();
        TOOLBAR.isLoaded();
        return this;
    }

    public SidebarWrapper getSidebar() {
        return SIDEBAR.isLoaded();
    }

    public ToolbarWrapper getToolbar() {
        return TOOLBAR;
    }
}
